import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { NotaPraticaComponent } from 'app/entities/nota-pratica/nota-pratica.component';
import { NotaPraticaService } from 'app/entities/nota-pratica/nota-pratica.service';
import { NotaPratica } from 'app/shared/model/nota-pratica.model';

describe('Component Tests', () => {
  describe('NotaPratica Management Component', () => {
    let comp: NotaPraticaComponent;
    let fixture: ComponentFixture<NotaPraticaComponent>;
    let service: NotaPraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [NotaPraticaComponent],
      })
        .overrideTemplate(NotaPraticaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotaPraticaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotaPraticaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NotaPratica(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.notaPraticas && comp.notaPraticas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

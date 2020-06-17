import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { TemplatePraticaComponent } from 'app/entities/template-pratica/template-pratica.component';
import { TemplatePraticaService } from 'app/entities/template-pratica/template-pratica.service';
import { TemplatePratica } from 'app/shared/model/template-pratica.model';

describe('Component Tests', () => {
  describe('TemplatePratica Management Component', () => {
    let comp: TemplatePraticaComponent;
    let fixture: ComponentFixture<TemplatePraticaComponent>;
    let service: TemplatePraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TemplatePraticaComponent],
      })
        .overrideTemplate(TemplatePraticaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TemplatePraticaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TemplatePraticaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TemplatePratica(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.templatePraticas && comp.templatePraticas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

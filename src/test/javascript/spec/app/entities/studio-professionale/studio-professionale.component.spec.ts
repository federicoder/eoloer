import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { StudioProfessionaleComponent } from 'app/entities/studio-professionale/studio-professionale.component';
import { StudioProfessionaleService } from 'app/entities/studio-professionale/studio-professionale.service';
import { StudioProfessionale } from 'app/shared/model/studio-professionale.model';

describe('Component Tests', () => {
  describe('StudioProfessionale Management Component', () => {
    let comp: StudioProfessionaleComponent;
    let fixture: ComponentFixture<StudioProfessionaleComponent>;
    let service: StudioProfessionaleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [StudioProfessionaleComponent],
      })
        .overrideTemplate(StudioProfessionaleComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StudioProfessionaleComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StudioProfessionaleService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new StudioProfessionale(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.studioProfessionales && comp.studioProfessionales[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { StudioProfessionaleDetailComponent } from 'app/entities/studio-professionale/studio-professionale-detail.component';
import { StudioProfessionale } from 'app/shared/model/studio-professionale.model';

describe('Component Tests', () => {
  describe('StudioProfessionale Management Detail Component', () => {
    let comp: StudioProfessionaleDetailComponent;
    let fixture: ComponentFixture<StudioProfessionaleDetailComponent>;
    const route = ({ data: of({ studioProfessionale: new StudioProfessionale(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [StudioProfessionaleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StudioProfessionaleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StudioProfessionaleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load studioProfessionale on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.studioProfessionale).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
